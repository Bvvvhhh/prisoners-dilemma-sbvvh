JAVAC=javac
JAVA=java
sources = $(wildcard src/*.java)
classes = $(sources:.java=.class)
CLASSPATH=src:libs/watchmaker-framework-0.7.1.jar:libs/uncommons-maths-1.2.3.jar
MAINCLASS=StringsExample

all: $(classes)

clean :
	rm -f *.class

run:
	$(JAVA) -cp $(CLASSPATH) $(MAINCLASS)

%.class : %.java
	$(JAVAC) -cp $(CLASSPATH) $<

