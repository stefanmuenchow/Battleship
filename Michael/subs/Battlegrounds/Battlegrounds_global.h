#ifndef BATTLEGROUNDS_GLOBAL_H
#define BATTLEGROUNDS_GLOBAL_H

#include <QtCore/qglobal.h>

#if defined(BATTLEGROUNDS_LIBRARY)
#  define BATTLEGROUNDSSHARED_EXPORT Q_DECL_EXPORT
#else
#  define BATTLEGROUNDSSHARED_EXPORT Q_DECL_IMPORT
#endif

#endif // BATTLEGROUNDS_GLOBAL_H
