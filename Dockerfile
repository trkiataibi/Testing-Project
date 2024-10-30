# استخدم صورة جافا الرسمية كأساس
FROM openjdk:17-jdk-slim

# إعداد مجلد العمل داخل الحاوية
WORKDIR /app

# نسخ ملف JAR إلى داخل الحاوية
COPY target/testing_project-0.0.1-SNAPSHOT.jar app.jar

# تعيين المنفذ الذي سيستمع عليه التطبيق داخل الحاوية
EXPOSE 8081

# تشغيل التطبيق عند بدء الحاوية
ENTRYPOINT ["java", "-jar", "app.jar"]