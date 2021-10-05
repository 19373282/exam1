FROM gcc:10
WORKDIR /app/
COPY cs.c ./
RUN gcc main.c -o main
RUN chmod +x main