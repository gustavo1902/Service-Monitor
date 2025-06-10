package com.projeto.service_monitor.service;

import com.projeto.service_monitor.model.MonitorTarget;
import com.projeto.service_monitor.repository.MonitorTargetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    private final MonitorTargetRepository repository;
    private final RestTemplate restTemplate;

    // Injejar a URL do webhook do n8n a partir do application.properties
    @Value("${n8n.webhook.url:}") 
    private String n8nWebhookUrl;

    public MonitoringService(MonitorTargetRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    
    @Scheduled(fixedRate = 60000)
    public void checkAllTargets() {
        logger.info("Iniciando verificação de todos os alvos...");
        List<MonitorTarget> targets = repository.findAll();
        for (MonitorTarget target : targets) {
            checkTarget(target);
        }
        logger.info("Verificação concluída.");
    }

    private void checkTarget(MonitorTarget target) {
        try {
            restTemplate.getForEntity(target.getUrl(), String.class);
            
            if (target.getStatus() == MonitorTarget.Status.DOWN) {
                logger.warn("SERVIÇO RECUPERADO: {}", target.getName());
            }
            target.setStatus(MonitorTarget.Status.UP);
            target.setLastErrorMessage(null);

        } catch (Exception e) {
            // Se a requisição falhou, o serviço está fora
            logger.error("FALHA no serviço: {} - URL: {} - Erro: {}", target.getName(), target.getUrl(), e.getMessage());
            target.setStatus(MonitorTarget.Status.DOWN);
            target.setLastErrorMessage(e.getMessage());
            sendAlert(target); // Envia o alerta para o n8n
        }

        target.setLastCheck(LocalDateTime.now());
        repository.save(target);
    }

    private void sendAlert(MonitorTarget target) {
        if (n8nWebhookUrl == null || n8nWebhookUrl.isBlank()) {
            logger.warn("URL do webhook do n8n não configurada. Alerta não enviado.");
            return;
        }

        try {
            // Envia os detalhes da falha como um POST para o webhook do n8n
            restTemplate.postForEntity(n8nWebhookUrl, target, String.class);
            logger.info("Alerta de falha para '{}' enviado para o n8n.", target.getName());
        } catch (Exception e) {
            logger.error("Falha ao enviar alerta para o n8n: {}", e.getMessage());
        }
    }
}