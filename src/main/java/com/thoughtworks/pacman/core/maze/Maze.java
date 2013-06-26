package com.thoughtworks.pacman.core.maze;

import java.awt.Dimension;
import java.util.Map;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.visitors.DotsLeftVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.ScoreTileVisitor;

public class Maze {
    private final int width;
    private final int height;
	private final Map<TileCoordinate, Tile> newTiles;

    Maze(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this.width = width;
        this.height = height;
        this.newTiles = tiles;
    }

    public boolean canMove(TileCoordinate tileCoordinate) {
    	return tileAt(tileCoordinate).isMovable();
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return new Dimension(width * Tile.SIZE, height * Tile.SIZE);
    }

    public int getScore() {
        ScoreTileVisitor scoreVisitor = new ScoreTileVisitor();
        int totalScore = 0;
        for (TileCoordinate tileCoordinate : newTiles.keySet()) {
        	totalScore += newTiles.get(tileCoordinate).visit(scoreVisitor);
		}
        return totalScore;
       
    }

    public boolean hasDotsLeft() {
        DotsLeftVisitor dotsLeftVisitor = new DotsLeftVisitor();
        int dotsLeft = 0;
        
        for (TileCoordinate tileCoordinate : newTiles.keySet()) {
        	dotsLeft += newTiles.get(tileCoordinate).visit(dotsLeftVisitor);
        }
        return dotsLeft > 0;
    }

    public Tile tileAt(TileCoordinate tileCoordinate) {
		if (newTiles.containsKey(tileCoordinate)){
			return newTiles.get(tileCoordinate);
		} else {
			return new EmptyTile(tileCoordinate);
		}
	}

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.append(newTiles.get(new TileCoordinate(x, y)));
            }
            result.append("\n");
        }

        return result.toString();
    }
}
