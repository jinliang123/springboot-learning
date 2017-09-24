package com.hand.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by jinliang on 2017/9/24.
 */
@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "com.hand.repository")
public class Neo4jConfig extends Neo4jConfiguration{

    @Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer("192.168.204.130:7474","neo4j","hadoop");
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new  SessionFactory("com.hand.domain");
    }
}
