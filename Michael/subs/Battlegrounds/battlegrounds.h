#ifndef BATTLEGROUNDS_H
#define BATTLEGROUNDS_H

#include <vector>
#include "Battlegrounds_global.h"

struct gridiron
{
    bool field[100];
};
typedef struct gridiron GRIDIRON;

struct psblPos
{
    int start;
    int end;
    bool vertical;
};
typedef struct psblPos PSBLPOS;

class BATTLEGROUNDSSHARED_EXPORT Battlegrounds
{
private:
    int _bs2Place;
    int _bc2Place;
    int _ds2Place;
    int _ss2Place;
    GRIDIRON _placements;
    int* _prePlacement;
    std::vector<PSBLPOS>* _psblBSPositions;
    std::vector<PSBLPOS>* _psblBCPositions;
    std::vector<PSBLPOS>* _psblDSPositions;
    std::vector<PSBLPOS>* _psblSSPositions;

    void updBSposblty ();
    void updBCposblty ();
    void updDSposblty ();
    void updSSposblty ();

    void setBS ();
    void setBC ();
    void setDS ();
    void setSS ();

    void placeShip (unsigned char start,
                    unsigned char end,
                    unsigned char size,
                    bool vertical);

    void convertGridiron();

public:
    Battlegrounds();
    GRIDIRON& Generate();
};

#endif // BATTLEGROUNDS_H
