# KBE5_AntMen Backend

**앤트맨(AntMen)** - 개미들의 공간을 위한 백엔드 API 서버입니다.

## 🎯 프로젝트 소개

안트맨은 **수요자(고객)와 공급자(매니저)를 연결하는 서비스 플랫폼**입니다. 고객이 원하는 서비스를 예약하고, 매니저가 해당 서비스를 제공하는 매칭 시스템을 운영합니다.

### 주요 기능
- **고객**: 서비스 예약, 결제, 리뷰 작성, 환불 요청
- **매니저**: 작업 관리, 스케줄 확인, 급여 정산  
- **관리자**: 전체 시스템 관리, 통계, 사용자 관리
- **공통**: 인증, 알림, 게시판, 파일 업로드 등 공통 기능

## 🏗️ 아키텍처

Spring Boot 기반의 **마이크로서비스 아키텍처**로 구성되어 있습니다.

```
KBE5_AntMen_BE/
├── m-common/          # 공통 서비스 (포트: 9090)
│   ├── 인증/인가      # JWT, Google OAuth 2.0
│   ├── 사용자 관리    # 고객/매니저 회원가입, 프로필
│   ├── 게시판        # 공지사항, 문의게시판
│   ├── 예약 관리      # 예약 생성, 조회, 상태 관리
│   ├── 매칭 시스템    # 매니저 추천, 매칭 처리
│   ├── 리뷰 시스템    # 리뷰 작성, 조회, 평점
│   ├── 결제 시스템    # 결제 처리, 환불 관리
│   ├── 알림 시스템    # 실시간 알림 (SSE)
│   └── 공통 기능      # 파일 업로드, 카테고리 관리
├── m-customer/        # 고객용 API (포트: 9091)
│   ├── 고객 프로필    # 프로필 조회/수정, 주소 관리
│   ├── 예약 관리      # 예약 생성, 조회, 취소
│   ├── 리뷰 관리      # 리뷰 작성, 수정, 삭제
│   ├── 매칭 관리      # 매니저 선택, 매칭 응답
│   ├── 결제 관리      # 결제 요청, 결제 정보 조회
│   └── 환불 관리      # 환불 요청, 환불 내역
├── m-manager/         # 매니저용 API (포트: 9092)
│   ├── 매니저 프로필  # 프로필 관리, 신원 확인
│   ├── 작업 관리      # 작업 수락, 진행, 완료
│   ├── 스케줄 관리    # 일정 확인, 급여 정산
│   └── 매칭 관리      # 매칭 요청, 상태 관리
├── m-admin/           # 관리자용 API (포트: 9093)
│   ├── 사용자 관리    # 고객/매니저 계정 관리
│   ├── 예약 관리      # 전체 예약 현황 관리
│   ├── 매칭 관리      # 수동 매칭, 추천 매칭
│   ├── 통계 관리      # 매출, 만족도, 환불률 분석
│   └── 시스템 관리    # 카테고리, 설정 관리
└── nginx/             # 리버스 프록시 (선택사항)
```

## 🛠️ 기술 스택

- **Framework**: Spring Boot 3.3.1
- **Language**: Java 17
- **Build Tool**: Gradle
- **Database**: MySQL 8.0
- **Security**: Spring Security + JWT + Google OAuth 2.0
- **Documentation**: Swagger/OpenAPI 3.0
- **File Storage**: AWS S3
- **Real-time**: Server-Sent Events (SSE)
- **Caching**: Redis (선택사항)

## 📋 사전 요구사항

- Java 17 이상
- Gradle 8.0 이상
- MySQL 8.0 이상
- Redis (선택사항)

## 🚀 설치 및 실행

### 1. 환경 변수 설정

```bash
# .env 파일 생성 (루트 디렉토리에)
cp .env.example .env

# 필수 환경 변수 설정
MYSQL_USERNAME=your_username
MYSQL_PASSWORD=your_password
MYSQL_DATABASE=antmen_db
MYSQL_PORT=3306
SPRING_DATABASE_URL=jdbc:mysql://localhost:3306/antmen_db?useSSL=false&serverTimezone=UTC

# JWT 설정
JWT_SECRET=your_jwt_secret_key

# Google OAuth 2.0
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
GOOGLE_REDIRECT_URI=http://localhost:4000/auth/google/callback

# AWS S3
S3_BUCKET_NAME=your_s3_bucket
S3_REGION=ap-northeast-2
S3_ACCESS_KEY=your_s3_access_key
S3_SECRET_KEY=your_s3_secret_key
```

### 2. 로컬 개발 환경

#### 전체 빌드
**macOS/Linux:**
```bash
./scripts/build.sh
```

**Windows:**
```cmd
scripts\build.bat
```

#### 개별 서비스 실행
```bash
# 공통 서비스 (인증, 공통 기능)
./gradlew :m-common:bootRun

# 고객용 API
./gradlew :m-customer:bootRun

# 매니저용 API  
./gradlew :m-manager:bootRun

# 관리자용 API
./gradlew :m-admin:bootRun
```

### 3. Docker 환경 (선택사항)

#### 전체 서비스 실행
```bash
docker-compose up -d
```

#### 개별 서비스 실행
```bash
# 특정 서비스만 실행
docker-compose up -d m-customer m-manager

# 로그 확인
docker-compose logs -f m-customer
```

## 🔧 API 서버 정보

| 서비스 | 포트 | 주요 기능 | Swagger URL |
|--------|------|-----------|-------------|
| m-common | 9090 | 인증, 공통 기능 | http://localhost:9090/swagger-ui.html |
| m-customer | 9091 | 고객 API | http://localhost:9091/swagger-ui.html |
| m-manager | 9092 | 매니저 API | http://localhost:9092/swagger-ui.html |
| m-admin | 9093 | 관리자 API | http://localhost:9093/swagger-ui.html |

## 📚 API 문서

각 서비스 실행 후 위의 Swagger URL에서 API 문서를 확인할 수 있습니다.

### 📖 Swagger API 명세서 바로가기
- **[공통 API 명세서](http://localhost:9090/swagger-ui.html)** - 인증, 사용자 관리, 게시판, 예약, 매칭, 리뷰, 결제, 알림
- **[고객 API 명세서](http://localhost:9091/swagger-ui.html)** - 고객 프로필, 예약, 리뷰, 매칭, 결제, 환불
- **[매니저 API 명세서](http://localhost:9092/swagger-ui.html)** - 매니저 프로필, 작업 관리, 스케줄, 매칭
- **[관리자 API 명세서](http://localhost:9093/swagger-ui.html)** - 사용자 관리, 예약 관리, 매칭 관리, 통계

### 주요 API 엔드포인트

#### 인증 관련 (m-common)
- `POST /api/v1/auth/login` - 일반 로그인
- `POST /api/v1/auth/google` - Google OAuth 로그인
- `POST /api/v1/auth/register` - 회원가입
- `POST /api/v1/auth/refresh` - 토큰 갱신

#### 고객 API (m-customer)
- `GET /customers/me` - 내 프로필 조회
- `PUT /customers/me` - 프로필 수정
- `GET /api/v1/customer/reservations` - 내 예약 목록
- `POST /api/v1/customer/reservations` - 예약 생성
- `POST /api/v1/customer/reviews` - 리뷰 작성
- `POST /api/v1/customer/refunds` - 환불 요청

#### 매니저 API (m-manager)
- `GET /api/v1/manager/tasks` - 작업 목록
- `PUT /api/v1/manager/tasks/{id}/accept` - 작업 수락
- `PUT /api/v1/manager/tasks/{id}/complete` - 작업 완료
- `GET /api/v1/manager/salary` - 급여 정산

#### 관리자 API (m-admin)
- `GET /api/v1/admin/users` - 사용자 관리
- `POST /api/v1/admin/matching/manual` - 수동 매칭
- `GET /api/v1/admin/statistics` - 통계 조회
- `PUT /api/v1/admin/users/{id}/status` - 사용자 상태 변경

#### 공통 기능 (m-common)
- `GET /api/v1/board/{boardType}/list` - 게시판 목록
- `POST /api/v1/board/{boardType}` - 게시글 작성
- `GET /api/v1/common/alerts/subscribe` - 실시간 알림 구독
- `GET /api/v1/common/categories` - 서비스 카테고리 조회

## 🗄️ 데이터베이스

### 주요 테이블 구조
- **users**: 사용자 정보 (고객/매니저/관리자)
- **customer_details**: 고객 상세 정보
- **manager_details**: 매니저 상세 정보
- **reservations**: 예약 정보
- **matchings**: 매칭 정보
- **reviews**: 리뷰 정보
- **payments**: 결제 정보
- **refunds**: 환불 정보
- **boards**: 게시판 정보
- **alerts**: 알림 정보

## 🔐 보안

### 인증 시스템
- **JWT 기반 인증**: Access Token + Refresh Token
- **Google OAuth 2.0**: 소셜 로그인 지원
- **Spring Security**: 역할 기반 접근 제어 (RBAC)

### 권한 체계
- **CUSTOMER**: 고객 권한
- **MANAGER**: 매니저 권한  
- **ADMIN**: 관리자 권한

## 🧪 테스트

```bash
# 전체 테스트 실행
./gradlew test

# 특정 모듈 테스트
./gradlew :m-customer:test
./gradlew :m-manager:test
./gradlew :m-admin:test
./gradlew :m-common:test
```

## 📦 배포

### 로컬 빌드
```bash
# 전체 빌드
./gradlew build

# 특정 모듈 빌드
./gradlew :m-customer:build
./gradlew :m-manager:build
./gradlew :m-admin:build
./gradlew :m-common:build
```

### Docker 배포 (선택사항)
```bash
# 전체 이미지 빌드
docker-compose build

# 특정 서비스 이미지 빌드
docker-compose build m-customer
```

## 🔍 모니터링 및 로그

### 로그 확인
```bash
# 로컬 실행 시 로그
./gradlew :m-customer:bootRun

# Docker 실행 시 로그
docker-compose logs m-customer
docker-compose logs -f m-customer
```

### 헬스 체크
```bash
# 각 서비스 헬스 체크
curl http://localhost:9090/actuator/health
curl http://localhost:9091/actuator/health
curl http://localhost:9092/actuator/health
curl http://localhost:9093/actuator/health
```

## 🐛 문제 해결

### 일반적인 문제들

1. **포트 충돌**
   ```bash
   # 사용 중인 포트 확인
   lsof -i :9090
   lsof -i :9091
   lsof -i :9092
   lsof -i :9093
   ```

2. **데이터베이스 연결 실패**
   - MySQL 서비스 상태 확인
   - 환경 변수 설정 확인
   - 데이터베이스 생성 확인

3. **메모리 부족**
   ```bash
   # JVM 힙 메모리 설정 조정
   export JAVA_OPTS="-Xmx512m -Xms256m"
   ```

## 📞 지원

문제가 발생하면 다음을 확인해주세요:
1. 로그 파일 확인
2. 환경 변수 설정 확인
3. 네트워크 연결 상태 확인
4. 데이터베이스 연결 상태 확인