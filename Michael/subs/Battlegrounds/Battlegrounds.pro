#-------------------------------------------------
#
# Project created by QtCreator 2013-04-19T23:44:09
#
#-------------------------------------------------

QT       += testlib

QT       -= gui

TARGET = Battlegrounds
TEMPLATE = lib

DEFINES += BATTLEGROUNDS_LIBRARY

SOURCES += battlegrounds.cpp

HEADERS += battlegrounds.h\
        Battlegrounds_global.h

symbian {
    MMP_RULES += EXPORTUNFROZEN
    TARGET.UID3 = 0xE65871F7
    TARGET.CAPABILITY = 
    TARGET.EPOCALLOWDLLDATA = 1
    addFiles.sources = Battlegrounds.dll
    addFiles.path = !:/sys/bin
    DEPLOYMENT += addFiles
}

unix:!symbian {
    maemo5 {
        target.path = /opt/usr/lib
    } else {
        target.path = /usr/lib
    }
    INSTALLS += target
}
