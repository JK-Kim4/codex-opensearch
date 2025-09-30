# Book API

Spring Boot 기반의 도서 정보 관리 API 서버 초기 프로젝트입니다. 현재 단계에서는 도메인 엔티티 설계와 기본적인 빌드 구성을 포함하고 있습니다.

## 주요 구성 요소
- Spring Boot 3.x (Web, Data JPA, Validation, Actuator)
- PostgreSQL 드라이버 (런타임)
- Gradle 빌드 스크립트

## 패키지 구조
```
src/main/java/com/example/bookapi
├── BookApiApplication.java
├── common/
│   └── AuditableEntity.java
└── entity/
    ├── enums/
    │   ├── AssetType.java
    │   ├── AvailabilityStatus.java
    │   ├── BookFormat.java
    │   ├── BookRelationType.java
    │   └── ReviewStatus.java
    ├── Author.java
    ├── Book.java
    ├── BookAsset.java
    ├── BookAuthor.java
    ├── BookAvailability.java
    ├── BookCategory.java
    ├── BookPrice.java
    ├── BookRatingSummary.java
    ├── BookReview.java
    ├── Category.java
    ├── Publisher.java
    ├── RelatedBook.java
    └── Series.java
```

## 다음 단계 아이디어
- JPA 엔티티 간 연관관계를 활용한 CRUD 서비스 및 REST 컨트롤러 구현
- Flyway/Liquibase 기반 스키마 관리
- OpenSearch 연동 구성 및 색인 도큐먼트 설계
