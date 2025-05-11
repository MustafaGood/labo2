# Använd en officiell Wildfly-bild som bas
FROM jboss/wildfly:latest

# Kopiera din applikation till Wildfly servern
COPY target/WarehouseAPI1-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

# Exponera porten som Wildfly kör på
EXPOSE 8080

# Starta Wildfly servern
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
