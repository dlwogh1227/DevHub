# 베이스 이미지로 OpenJDK 17 사용
FROM openjdk:17-jdk-slim

# 패키지 리스트 업데이트 및 ffmpeg 설치
RUN apt-get update && apt-get install -y ffmpeg

# 작업 디렉토리 설정
WORKDIR /app

# /app/data 경로는 컨테이너 내에서 데이터가 저장될 위치
# 로컬 D:\gallery와 마운트 할 예정
VOLUME ["/app/data"]

# JAR 파일을 컨테이너로 복사 (여기서는 myapp.jar 파일을 예로 듬)
COPY DevHub-0.0.1.jar /app/myapp.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]
