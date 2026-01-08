package com.marcocipo.worksync.config;

import com.marcocipo.worksync.domain.Group;
import com.marcocipo.worksync.domain.Role;
import com.marcocipo.worksync.domain.User;
import com.marcocipo.worksync.repository.GroupRepository;
import com.marcocipo.worksync.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Set;

/**
 * DevDataInitializer
 * TODO nacher raus aus projekt?
 * Zweck:
 * - Erzeugt initiale Beispieldaten in der Entwicklungsumgebung (Profil "dev"),
 .
 */
@Profile("dev")
@Configuration
public class DevDataInitializer {

    @Bean
    CommandLineRunner seed(UserRepository userRepo, GroupRepository groupRepo) {
        return args -> {
            // Idempotenz: Falls bereits Daten existieren, legen wir nichts doppelt an.
            if (userRepo.count() > 0) {
                return;
            }

            // --- Users anlegen ---
            User user1 = new User();
            user1.setEmail("user1@worksync.dev");
            user1.setPasswordHash("dev-only"); // später: BCrypt-Hash
            user1.setRole(Role.USER);

            User user2 = new User();
            user2.setEmail("user2@worksync.dev");
            user2.setPasswordHash("dev-only");
            user2.setRole(Role.USER);

            User admin = new User();
            admin.setEmail("admin@worksync.dev");
            admin.setPasswordHash("dev-only");
            admin.setRole(Role.ADMIN);

            userRepo.saveAll(Set.of(user1, user2, admin));

            // --- Groups anlegen ---
            Group teamAlpha = new Group();
            teamAlpha.setName("TEAM_ALPHA");
            teamAlpha.getMembers().add(user1);
            teamAlpha.getMembers().add(admin);

            Group teamBeta = new Group();
            teamBeta.setName("TEAM_BETA");
            teamBeta.getMembers().add(user2);
            teamBeta.getMembers().add(admin);

            groupRepo.saveAll(Set.of(teamAlpha, teamBeta));
        };
    }
}
