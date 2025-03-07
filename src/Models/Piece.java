package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Piece {
    private IntegerProperty piece;

    public Piece(int initialPiece) {
        this.piece = new SimpleIntegerProperty(initialPiece);
    }

    public int getPiece() {
        return piece.get();
    }

    public void setPiece(int piece) {
        this.piece.set(piece);
    }

    public IntegerProperty pieceProperty() {
        return piece;
    }
}