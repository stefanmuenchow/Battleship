#include "battlegrounds.h"
#include <cstring>
#include <stdio.h>


Battlegrounds::Battlegrounds ()
    :     _bs2Place(int(1)),
          _bc2Place(int(2)),
          _ds2Place(int(3)),
          _ss2Place(int(4)),
          _prePlacement(new int[100])
{

    //memset (&_placements.field, false, sizeof(bool) *100);
    //memset (_prePlacement, 0, sizeof(unsigned char) *100);
    for (int i = 0; i < 100; ++i)
    {
        _placements.field[i] = false;
        _prePlacement[i] = 0;
    }
    //_placements = new GRIDIRON;
    //_prePlacement = new unsigned char[100];

    _psblBSPositions = new std::vector<PSBLPOS>;
    _psblBCPositions = new std::vector<PSBLPOS>;
    _psblDSPositions = new std::vector<PSBLPOS>;
    _psblSSPositions = new std::vector<PSBLPOS>;
}

GRIDIRON& Battlegrounds::Generate()
{
    updBSposblty();
    updBCposblty();
    updDSposblty();
    updSSposblty();

    setBS();
    setBC();
    setDS();
    setSS();

    convertGridiron();

    printf ("\n\n");
    for (int k = 0; k < 100;++k)
    {
        if (k != 0 && !(k % 10))
        {
            printf("\n");
        }
        printf("%d ", _prePlacement[k]);
    }

    return _placements;
}


void Battlegrounds::updBSposblty()
{
    _psblBSPositions->clear();

    // find vertical spaces
    for (int i = 0;
         i < 60;
         ++i)
    {
        if (!_prePlacement[i]    &&
            !_prePlacement[i+10] &&
            !_prePlacement[i+20] &&
            !_prePlacement[i+30] &&
            !_prePlacement[i+40]   )
        {
            PSBLPOS newPos;
            newPos.start = i;
            newPos.end = i + 40;
            newPos.vertical = true;

            _psblBSPositions->push_back (newPos);
        }
    }

    // find horizontal spaces
    for (int j = 0; j < 95;)
    {
        if (!_prePlacement[j]   &&
            !_prePlacement[j+1] &&
            !_prePlacement[j+2] &&
            !_prePlacement[j+3] &&
            !_prePlacement[j+4]   )
        {
            PSBLPOS newPos;
            newPos.start = j;
            newPos.end = j + 4;
            newPos.vertical = false;

            _psblBSPositions->push_back (newPos);
        }

        if (j % 10 == 5)
        {
            j += 5;
        }
        else
        {
            ++j;
        }
    }
}

void Battlegrounds::updBCposblty()
{
    _psblBCPositions->clear();

    // find vertical spaces
    for (int i = 0;
         i < 70;
         ++i)
    {
        if (!_prePlacement[i]    &&
            !_prePlacement[i+10] &&
            !_prePlacement[i+20] &&
            !_prePlacement[i+30]   )
        {
            PSBLPOS newPos;
            newPos.start = i;
            newPos.end = i + 30;
            newPos.vertical = true;

            _psblBCPositions->push_back (newPos);
        }
    }

    // find horizontal spaces
    for (int j = 0; j < 96;)
    {
        if (!_prePlacement[j]   &&
            !_prePlacement[j+1] &&
            !_prePlacement[j+2] &&
            !_prePlacement[j+3]   )
        {
            PSBLPOS newPos;
            newPos.start = j;
            newPos.end = j + 3;
            newPos.vertical = false;

            _psblBCPositions->push_back (newPos);
        }

        if (j % 10 == 6)
        {
            j += 4;
        }
        else
        {
            ++j;
        }
    }
}

void Battlegrounds::updDSposblty()
{
    _psblDSPositions->clear();

    // find vertical spaces
    for (int i = 0;
         i < 80;
         ++i)
    {
        if (!_prePlacement[i]    &&
            !_prePlacement[i+10] &&
            !_prePlacement[i+20]   )
        {
            PSBLPOS newPos;
            newPos.start = i;
            newPos.end = i + 20;
            newPos.vertical = true;

            _psblDSPositions->push_back (newPos);
        }
    }

    // find horizontal spaces
    for (int j = 0; j < 97;)
    {
        if (!_prePlacement[j]   &&
            !_prePlacement[j+1] &&
            !_prePlacement[j+2]   )
        {
            PSBLPOS newPos;
            newPos.start = j;
            newPos.end = j + 2;
            newPos.vertical = false;

            _psblDSPositions->push_back (newPos);
        }

        if (j % 10 == 7)
        {
            j += 3;
        }
        else
        {
            ++j;
        }
    }
}

void Battlegrounds::updSSposblty()
{
    _psblSSPositions->clear();

    // find vertical spaces
    for (int i = 0;
         i < 60;
         ++i)
    {
        if (!_prePlacement[i]    &&
            !_prePlacement[i+10]   )
        {
            PSBLPOS newPos;
            newPos.start = i;
            newPos.end = i + 10;
            newPos.vertical = true;

            _psblSSPositions->push_back (newPos);
        }
    }

    // find horizontal spaces
    for (int j = 0; j < 98;)
    {
        if (!_prePlacement[j]   &&
            !_prePlacement[j+1]   )
        {
            PSBLPOS newPos;
            newPos.start = j;
            newPos.end = j + 1;
            newPos.vertical = false;

            _psblSSPositions->push_back (newPos);
        }

        if (j % 10 == 8)
        {
            j += 2;
        }
        else
        {
            ++j;
        }
    }
}

void Battlegrounds::setBS()
{
    unsigned char pos = rand () % (_psblBSPositions->size());

    PSBLPOS chosen = _psblBSPositions->at (pos);

    placeShip (chosen.start,
               chosen.end,
               5,
               chosen.vertical);

    --_bs2Place;

    updBCposblty();
    updDSposblty();
    updSSposblty();
}

void Battlegrounds::setBC()
{
    while (_bc2Place > 0)
    {
        unsigned char pos = rand () % (_psblBCPositions->size());
        PSBLPOS chosen = _psblBCPositions->at (pos);

        placeShip (chosen.start,
                   chosen.end,
                   4,
                   chosen.vertical);

        --_bc2Place;

        updBCposblty();
    }

    updDSposblty();
    updSSposblty();
}

void Battlegrounds::setDS()
{
    while (_ds2Place > 0)
    {
        unsigned char pos = rand () % (_psblDSPositions->size());
        PSBLPOS chosen = _psblDSPositions->at (pos);

        placeShip (chosen.start,
                   chosen.end,
                   3,
                   chosen.vertical);

        --_ds2Place;

        updDSposblty();
    }

    updSSposblty();
}

void Battlegrounds::setSS()
{
    while (_ss2Place > 0)
    {
        unsigned char pos = rand () % (_psblSSPositions->size());
        PSBLPOS chosen = _psblSSPositions->at (pos);

        placeShip (chosen.start,
                   chosen.end,
                   2,
                   chosen.vertical);

        --_ss2Place;

        updSSposblty();
    }
}

void Battlegrounds::placeShip (unsigned char start,
                               unsigned char end,
                               unsigned char size,
                               bool vertical)
{
    if (vertical)
    {
        for (int i = 0;
             i < size;
             ++i)
        {
            _prePlacement[start + i * 10] = 5;
            if ((((start + i * 10) - 1) / 10) ==
                ((start + i * 10) / 10)         )
            {
                ++_prePlacement[(start + i * 10) - 1];
            }
            if ((((start + i * 10) + 1) / 10) ==
                ((start + i * 10) / 10)         )
            {
                ++_prePlacement[(start + i * 10) + 1];
            }
        }

        if ((start - 10) >= 0)
        {
            ++_prePlacement[start - 10];

            if (((start - 9) / 10) == ((start -10) / 10))
            {
                ++_prePlacement[start - 9];
            }

            if (((start - 11) >= 0)     &&
                (((start - 11) / 10) ==
                 ((start -10) / 10)    )  )
            {
                ++_prePlacement[start - 11];
            }
        }

        if ((end + 10) < 100)
        {
            ++_prePlacement[end + 10];

            if (((end + 9) / 10) == ((end + 10) / 10))
            {
                ++_prePlacement[end + 9];
            }

            if (((end + 11) < 100)    &&
                (((end + 11) / 10) ==
                 ((end + 10) / 10)   )  )
            {
                ++_prePlacement[end + 11];
            }
        }
    }
    else
    {
        for (int i = 0; i < size; ++i)
        {
            _prePlacement[start + i] = 5;
            if (((start + i) - 10) >= 0)
            {
                ++_prePlacement[(start + i) - 10];
            }
            if (((start + i) + 10) < 100)
            {
                ++_prePlacement[(start + i) + 10];
            }
        }

        if (((start - 1) > 0)     &&
            ((start - 1) / 10) ==
            (start / 10)            )
        {
            ++_prePlacement[start - 1];

            if ((start - 11) >= 0)
            {
                ++_prePlacement[start - 11];
            }

            if ((start + 9) < 100)
            {
                ++_prePlacement[start + 9];
            }
        }

        if (((end + 1) < 100)  &&
            ((end + 1) / 10) ==
            (end / 10)           )
        {
            ++_prePlacement[end + 1];

            if ((end + 11) < 100)
            {
                ++_prePlacement[end + 11];
            }

            if ((end - 9) >= 0)
            {
                ++_prePlacement[end - 9];
            }
        }
    }
}

void Battlegrounds::convertGridiron()
{
    for (int i = 0; i < 100; ++i)
    {
       _placements.field[i] = _prePlacement[i] == 5;
    }
}
