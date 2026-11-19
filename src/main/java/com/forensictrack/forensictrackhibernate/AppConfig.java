
package com.forensictrack.forensictrackhibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }

    @Bean
    public CaseDAO caseDAO() {
        return new CaseDAO();
    }

    @Bean
    public EvidenceDAO evidenceDAO() {
        return new EvidenceDAO();
    }

    @Bean
    public CaseNoteDAO caseNoteDAO() {
        return new CaseNoteDAO();
    }

    @Bean
    public ChainOfCustodyDAO chainOfCustodyDAO() {
        return new ChainOfCustodyDAO();
    }

    @Bean
    public InvestigationOverviewDAO investigationOverviewDAO() {
        return new InvestigationOverviewDAO();
    }
}
