EXT        = java
JCC        = javac

JCFLAGS    = -Werror -deprecation

JDOC       = javadoc
JDOCFLAGS  = -author -quiet

MAIN       = Main
JAR        = distri.jar

SRCDIR     = src
CLSDIR     = cls
DOCDIR     = doc
JARDIR     = jar

SRC        = $(wildcard $(SRCDIR)/*.$(EXT))
CLASSES    = $(SRC:$(SRCDIR)/%.$(EXT)=$(CLSDIR)/%.class)
CLSNODIR   = $(CLASSES:$(CLSDIR)/%=%)

default:   $(CLASSES)
jar:       $(JAR)
all:       $(JAR) doc

.PHONY: doc clean mrproper

help:
	@ echo 'Makefile for IPRO project `distri`'
	@ echo ''
	@ echo 'Usage :'
	@ echo 'make             Build .class files'
	@ echo 'make jar         Build .jar file'
	@ echo 'make doc         Build documentation'
	@ echo 'make all         Build .class and .jar files, and documentation'
	@ echo 'make clean       Remove .class files'
	@ echo 'make mrproper    Remove all build directories'

$(JAR): $(CLASSES)
	@ [ -d $(JARDIR) ] || mkdir $(JARDIR)
	@ jar cfe $(JARDIR)/$@ $(MAIN) -C $(CLSDIR) .
	@ [ -h $@ ] || ln -s $(JARDIR)/$@ $@

$(CLSDIR)/%.class: $(SRC)
	@ [ -d $(CLSDIR) ] || mkdir $(CLSDIR)
	@ $(JCC) $(JCFLAGS) -d $(CLSDIR) $(SRC)

doc:
	@ [ -d $(DOCDIR) ] || mkdir $(DOCDIR)
	@ $(JDOC) $(JDOCFLAGS) -d $(DOCDIR) $(SRC)

clean:
	@ rm -f $(CLSDIR)/*.class

mrproper: clean
	@ rm -rf $(JAR) $(CLSDIR) $(DOCDIR) $(JARDIR)

