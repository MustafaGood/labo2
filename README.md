# WarehouseAPI1 – Laboration 2 Docker

Detta projekt är en Java-baserad webbapplikation som körs på en Wildfly-server och är paketerad som en Docker-image för enkel driftsättning.

## Bygga och köra applikationen

### 1. Bygg projektet med Maven

```sh
./mvnw clean package
```

### 2. Bygg Docker-imagen

```sh
docker build -t mustafa1929/labo2:latest .
```

### 3. Kör applikationen med Docker

```sh
docker run -d -p 8090:8080 mustafa1929/labo2:latest
```

Applikationen nås på: [http://localhost:8090](http://localhost:8090)

> Om port 8090 är upptagen, byt ut till en annan ledig port, t.ex. `-p 8091:8080`.

## Länkar

- **Docker Hub:** [https://hub.docker.com/r/mustafa1929/labo2](https://hub.docker.com/r/mustafa1929/labo2)
- **GitHub:** [https://github.com/MustafaGood/labo2](https://github.com/MustafaGood/labo2)

## Om projektet

- Applikationen är paketerad som en `.war`-fil och deployas automatiskt till Wildfly via Dockerfile.
- Wildfly startar automatiskt när containern körs.
- Du kan anropa API:et via t.ex. `curl` eller webbläsare.

## Exempel på API-anrop

```sh
curl http://localhost:8090/api/warehouse
```

_Byt ut `/api/warehouse` mot det endpoint som finns i din applikation._ 