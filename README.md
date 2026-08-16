# web01

Spring Boot demo (`Web01/`).

## Configuration

Database credentials are read from the environment, not from `application.yml`:

| Variable | Required | Default |
| --- | --- | --- |
| `DB_URL` | no | `jdbc:mysql://localhost:3306/web01` |
| `DB_USERNAME` | yes | – |
| `DB_PASSWORD` | yes | – |

```bash
cd Web01
DB_USERNAME=app DB_PASSWORD=... mvn spring-boot:run
```

Use a least-privilege MySQL account (not `root`) for the application.
