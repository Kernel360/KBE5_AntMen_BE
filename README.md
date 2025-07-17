# 🐜 AntMen Backend

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-green?style=for-the-badge&logo=spring-boot)
![Gradle](https://img.shields.io/badge/Gradle-8.0-blue?style=for-the-badge&logo=gradle)
![JPA](https://img.shields.io/badge/JPA-Hibernate-purple?style=for-the-badge)
![JWT](https://img.shields.io/badge/JWT-Auth-yellow?style=for-the-badge)

**취준생들이 만든 청소 서비스 플랫폼의 백엔드 시스템** 🚀

</div>

---

## 📋 목차

- [프로젝트 소개](#-프로젝트-소개)
- [기술 스택](#-기술-스택)
- [프로젝트 구조](#-프로젝트-구조)
- [시작하기](#-시작하기)
- [API 문서](#-api-문서)
- [개발 가이드](#-개발-가이드)
- [배포](#-배포)
- [기여하기](#-기여하기)

---

## 🎯 프로젝트 소개

**AntMen**은 바쁜 일상에서 효율적으로 청소할 수 있도록 도와주는 서비스 플랫폼입니다. 

### 주요 기능
- 🏠 **다양한 청소 서비스**: 가사청소, 주방청소, 상업공간청소, 입주청소, 육아서비스
- 👥 **역할별 시스템**: 고객, 매니저, 관리자 각각의 역할에 맞는 기능 제공
- 💳 **결제 시스템**: 안전하고 편리한 결제 처리
- 📱 **실시간 알림**: 서비스 상태 및 예약 관련 실시간 알림
- ⭐ **리뷰 시스템**: 서비스 품질 향상을 위한 리뷰 및 평점 시스템

### 아키텍처
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   m-customer    │    │   m-manager     │    │    m-admin      │
│   (고객용)      │    │   (매니저용)    │    │   (관리자용)    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │   m-common      │
                    │   (공통 모듈)   │
                    └─────────────────┘
```

---

## 🛠 기술 스택

### Backend
- **Java 17** - 안정적이고 성능이 뛰어난 언어
- **Spring Boot 3.3.1** - 빠른 개발과 배포를 위한 프레임워크
- **Spring Security** - 보안 인증 및 권한 관리
- **Spring Data JPA** - 데이터베이스 접근 및 관리
- **JWT** - 토큰 기반 인증
- **Gradle** - 빌드 도구

### Database & Tools
- **MySQL** - 관계형 데이터베이스
- **Swagger/OpenAPI** - API 문서화
- **Docker** - 컨테이너화
- **Nginx** - 리버스 프록시

### Development Tools
- **Lombok** - 보일러플레이트 코드 제거
- **Dotenv** - 환경변수 관리
- **JUnit 5** - 단위 테스트

---

## 📁 프로젝트 구조

```
KBE5_AntMen_BE/
├── 📦 m-admin/          # 관리자 서비스
│   ├── src/main/java/
│   └── src/main/resources/
├── 📦 m-common/         # 공통 모듈
│   ├── src/main/java/
│   └── src/main/resources/
├── 📦 m-customer/       # 고객 서비스
│   ├── src/main/java/
│   └── src/main/resources/
├── 📦 m-manager/        # 매니저 서비스
│   ├── src/main/java/
│   └── src/main/resources/
├── 🐳 nginx/            # Nginx 설정
├── 📜 scripts/          # 빌드 스크립트
├── 📄 build.gradle      # 루트 빌드 설정
└── 🐳 docker-compose.yml
```

### 모듈별 역할

| 모듈 | 포트 | 역할 | 주요 기능 |
|------|------|------|-----------|
| `m-customer` | 9091 | 고객 서비스 | 예약, 결제, 리뷰 |
| `m-manager` | 9092 | 매니저 서비스 | 작업 관리, 수익 정산 |
| `m-admin` | 9093 | 관리자 서비스 | 전체 관리, 통계 |
| `m-common` | 9090 | 공통 모듈 | 공통 기능, 설정 |

---

## 🚀 시작하기

### 1. 사전 요구사항

- **Java 17** 이상
- **Gradle 8.0** 이상
- **Docker & Docker Compose** (선택사항)
- **MySQL 8.0** 이상

### 2. 프로젝트 클론

```bash
git clone https://github.com/your-username/KBE5_AntMen_BE.git
cd KBE5_AntMen_BE
```

### 3. 환경 설정

#### 3.1 환경변수 파일 생성

각 모듈의 `src/main/resources/` 디렉토리에 환경변수 파일을 생성하세요:

```bash
# .env 파일 예시
DB_HOST=localhost
DB_PORT=3306
DB_NAME=antmen_db
DB_USERNAME=root
DB_PASSWORD=your_password

JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION=86400000
```

#### 3.2 데이터베이스 설정

```sql
CREATE DATABASE antmen_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 4. 빌드 및 실행

#### 4.1 전체 프로젝트 빌드

**macOS/Linux:**
```bash
./scripts/build.sh
```

**Windows:**
```cmd
scripts\build.bat
```

#### 4.2 개별 모듈 실행

```bash
# 고객 서비스 실행
./gradlew :m-customer:bootRun

# 매니저 서비스 실행
./gradlew :m-manager:bootRun

# 관리자 서비스 실행
./gradlew :m-admin:bootRun
```

#### 4.3 Docker로 실행

```bash
# 전체 서비스 실행
docker-compose up -d

# 특정 서비스만 실행
docker-compose up -d m-customer m-manager
```

### 5. 서비스 접속

| 서비스 | URL | 설명 |
|--------|-----|------|
| 고객 API | http://localhost:8081 | 고객용 REST API |
| 매니저 API | http://localhost:8082 | 매니저용 REST API |
| 관리자 API | http://localhost:8083 | 관리자용 REST API |
| Swagger UI | http://localhost:8081/swagger-ui.html | API 문서 |

---

## 📚 API 문서

### Swagger UI 접속

각 서비스의 Swagger UI에 접속하여 API 문서를 확인할 수 있습니다:

- **고객 API**: http://localhost:8081/swagger-ui.html
- **매니저 API**: http://localhost:8082/swagger-ui.html
- **관리자 API**: http://localhost:8083/swagger-ui.html

### 주요 API 엔드포인트

#### 인증 관련
```
POST /api/auth/login          # 로그인
POST /api/auth/refresh        # 토큰 갱신
POST /api/auth/logout         # 로그아웃
```

#### 예약 관련
```
GET    /api/reservations      # 예약 목록 조회
POST   /api/reservations      # 예약 생성
PUT    /api/reservations/{id} # 예약 수정
DELETE /api/reservations/{id} # 예약 취소
```

#### 결제 관련
```
POST /api/payments/process    # 결제 처리
GET  /api/payments/history    # 결제 내역
```

---

## 👨‍💻 개발 가이드

### 코드 컨벤션

#### 1. 패키지 구조
```
com.antmen.antwork.{module}/
├── controller/     # REST API 컨트롤러
├── service/        # 비즈니스 로직
├── repository/     # 데이터 접근 계층
├── entity/         # JPA 엔티티
├── dto/           # 데이터 전송 객체
├── config/        # 설정 클래스
└── util/          # 유틸리티 클래스
```

#### 2. 네이밍 컨벤션
- **클래스명**: PascalCase (예: `UserService`)
- **메서드명**: camelCase (예: `getUserById`)
- **변수명**: camelCase (예: `userName`)
- **상수명**: UPPER_SNAKE_CASE (예: `MAX_RETRY_COUNT`)

#### 3. 주석 작성
```java
/**
 * 사용자 정보를 조회합니다.
 * 
 * @param userId 조회할 사용자 ID
 * @return 사용자 정보 (없으면 null)
 * @throws UserNotFoundException 사용자를 찾을 수 없는 경우
 */
public User getUserById(Long userId) {
    // 구현 내용
}
```

### 테스트 작성

#### 1. 단위 테스트
```bash
# 전체 테스트 실행
./gradlew test

# 특정 모듈 테스트 실행
./gradlew :m-customer:test
```

#### 2. 통합 테스트
```bash
# 통합 테스트 실행
./gradlew integrationTest
```

### 로그 확인

```bash
# 실시간 로그 확인
tail -f logs/m-customer/application.log
tail -f logs/m-manager/application.log
tail -f logs/m-admin/application.log
```

---

## 🚀 배포

### 1. 프로덕션 빌드

```bash
# 전체 프로젝트 빌드
./gradlew clean build -x test

# JAR 파일 생성 확인
ls -la m-customer/build/libs/
ls -la m-manager/build/libs/
ls -la m-admin/build/libs/
```

### 2. Docker 이미지 빌드

```bash
# 전체 이미지 빌드
docker-compose build

# 특정 서비스 이미지 빌드
docker build -t antmen-customer ./m-customer
docker build -t antmen-manager ./m-manager
docker build -t antmen-admin ./m-admin
```

### 3. 프로덕션 배포

```bash
# 환경변수 설정
export SPRING_PROFILES_ACTIVE=prod

# Docker Compose로 배포
docker-compose -f docker-compose.prod.yml up -d
```

---

## 🤝 기여하기

### 1. 이슈 리포트

버그를 발견하거나 새로운 기능을 제안하고 싶다면 [Issues](../../issues)를 통해 알려주세요.

### 2. 풀 리퀘스트

1. 이 저장소를 포크합니다
2. 새로운 브랜치를 생성합니다 (`git checkout -b feature/amazing-feature`)
3. 변경사항을 커밋합니다 (`git commit -m 'Add amazing feature'`)
4. 브랜치에 푸시합니다 (`git push origin feature/amazing-feature`)
5. 풀 리퀘스트를 생성합니다

### 3. 개발 환경 설정

```bash
# 개발 브랜치 생성
git checkout -b develop

# 의존성 설치
./gradlew build

# 개발 서버 실행
./gradlew :m-customer:bootRun
```

---

## 📞 문의 및 지원

### 팀 정보
- **프로젝트**: AntMen Backend
- **개발자**: 취준생 개발팀 🎓
- **기술 스택**: Java, Spring Boot, JPA, JWT

### 연락처
- **이메일**: antmen.dev@gmail.com
- **GitHub**: [@antmen-team](https://github.com/antmen-team)

### 라이선스
이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

---

<div align="center">

**취준생들의 열정으로 만든 프로젝트입니다! 🚀**

⭐ **이 프로젝트가 도움이 되었다면 스타를 눌러주세요!** ⭐

</div>