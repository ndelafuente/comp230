CC = javac
CFLAGS = -Xlint

TARGETS = Compress Decompress

all: $(TARGETS)

Compress: Compress.java
	$(CC) $(CFLAGS) $^

Decompress: Decompress.java
	$(CC) $(CFLAGS) $^

clean:
	$(RM) *.class *.zzz *.log

