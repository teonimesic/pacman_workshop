package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public interface TileVisitor<T> {
    T visit(Dot dot);
    T visit(Wall wall);
    T visit(EmptyTile emptyTile);
    T visit(Door door);
}
