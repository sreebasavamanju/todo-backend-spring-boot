package com.learn.todobackend.resource;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;

public class CassandraConnector {
	private Cluster cluster;
	private Session session;

	public CassandraConnector() {
		cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).build();
		session = cluster.connect();
	}

	public CassandraConnector(String node, Integer port) {
		Builder b = Cluster.builder().addContactPoint(node);
		if (port != null) {
			b.withPort(port);
		}
		cluster = b.build();

		session = cluster.connect();
	}

	public Session getSession() {
		return this.session;
	}

	public void close() {
		session.close();
		cluster.close();
	}
}
