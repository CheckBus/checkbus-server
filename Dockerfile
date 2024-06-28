FROM openjdk:17

COPY build/libs/check-bus-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ \
    "java", \
    "-Dkey.env.path=/environment/check-bus-key.yaml", \
    "-jar", \
    "/app.jar" \
]