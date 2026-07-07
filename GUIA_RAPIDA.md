# 🚀 Guía Rápida - LMS Microservicios

## ✅ Estructura del Proyecto

```
proyecto-final-modulo-3/
├── course-service/           → Puerto 8083 | coursedb (5440)
├── enrollment-service/       → Puerto 8084 | enrollmentdb (5441)
├── payment-service/          → Puerto 8085 | paymentdb (5442)
├── notification-service/     → Puerto 8086 | notificationdb (5443)
├── user-service/             → Puerto 8081 | userdb (5434)
├── postman/                  → Colección Postman
└── docker-compose.yml        → Kafka + 5 PostgreSQL + Herramientas
```

---

## 🐳 Paso 1: Levantar Docker

```bash
cd proyecto-final-modulo-3
docker compose up -d
```

**Espera 30 segundos** para que Kafka y PostgreSQL inicialicen.

---

## 🗄️ Paso 2: Crear tablas en DBeaver

### Conectar a cada BD:

| BD | Puerto | Database | Usuario | Contraseña |
|---|---|---|---|---|
| userdb | 5434 | postgres → luego userdb | postgres | postgres |
| coursedb | 5440 | postgres → luego coursedb | postgres | postgres |
| enrollmentdb | 5441 | postgres → luego enrollmentdb | postgres | postgres |
| paymentdb | 5442 | postgres → luego paymentdb | postgres | postgres |
| notificationdb | 5443 | postgres → luego notificationdb | postgres | postgres |

### Ejecutar scripts SQL:

Ver archivo: `TRABAJO_FINAL.md` → sección "SCRIPTS SQL DE TABLAS POR MICROSERVICIO"

---

## ⚙️ Paso 3: Compilar microservicios

```bash
cd user-service && ./mvnw clean package -DskipTests && cd ..
cd course-service && ./mvnw clean package -DskipTests && cd ..
cd enrollment-service && ./mvnw clean package -DskipTests && cd ..
cd payment-service && ./mvnw clean package -DskipTests && cd ..
cd notification-service && ./mvnw clean package -DskipTests && cd ..
```

---

## ▶️ Paso 4: Ejecutar microservicios (5 terminales)

```bash
# Terminal 1
cd user-service && ./mvnw spring-boot:run

# Terminal 2
cd course-service && ./mvnw spring-boot:run

# Terminal 3
cd enrollment-service && ./mvnw spring-boot:run

# Terminal 4
cd payment-service && ./mvnw spring-boot:run

# Terminal 5
cd notification-service && ./mvnw spring-boot:run
```

---

## 📬 Paso 5: Probar con Postman

1. Importa: `postman/LMS_Microservices.postman_collection.json`
2. Ejecuta los requests en orden:
   - `01 - Crear Usuario`
   - `02 - Crear Curso`
   - `02 - PUBLICAR Curso`
   - `03 - Crear Matrícula`
   - `04 - Registrar Pago APROBADO`
   - `05 - Verificar Matricula CONFIRMADA`

---

## 🌐 URLs útiles

| Herramienta | URL |
|---|---|
| **Kafka UI** | http://localhost:8090 |
| **pgAdmin** | http://localhost:5051 (admin@lms.com / admin) |


