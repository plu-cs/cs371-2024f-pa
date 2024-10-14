package cardtrick;

public class Card implements Comparable<Card> {
    private final int value; // 1 - Ace, 2 - 10, 11 - Jack, 12 - Queen, 13 - King
    private final char suit; // C, D, H, S

    /**
     * Constructs a Card using a string representing the card's value and suit.
     * Examples: "10D", "5H", "AC", "KS", etc.
     *
     * @param s the string representing the card's value and suit
     */
    public Card( String s ) {
        if( s.length() < 2 ) throw new IllegalArgumentException("Invalid Card string: " + s);
        suit = s.charAt(s.length() - 1);
        if( suit != 'C' && suit != 'D' && suit != 'H' && suit != 'S' )
            throw new IllegalArgumentException("Invalid Card string: " + s);

        String valueStr = s.substring(0, s.length() - 1);
        if( valueStr.equals("J") ) {
            value = 11;
        } else if(valueStr.equals("Q")) {
            value = 12;
        } else if(valueStr.equals("K") ) {
            value = 13;
        } else if( valueStr.equals("A") ) {
            value = 1;
        } else {
            try {
                value = Integer.parseInt(valueStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid Card string: " + s);
            }
            if (value < 2 || value > 10) {
                throw new IllegalArgumentException("Card value is invalid: " + s);
            }
        }
    }

    public int getValue() { return value; }

    public char getSuit() { return suit; }

    /**
     * Compares this to other, based on value, then on suit.  Cards are ordered
     * by value in the order: A, 2, 3, ..., 10, J, Q, K.  For Cards with equal
     * value, they are ordered by suit in the order: C, D, H, S.
     *
     * @param other the object to be compared.
     * @return positive value if this is greater than other, negative if this is
     * less than other, and 0 if they are equal.
     */
    @Override
    public int compareTo(Card other) {
        if( value == other.value ) return this.suit - other.suit;
        return this.value - other.value;
    }

    /**
     * @return a String representation of this Card.
     */
    @Override
    public String toString() {
        String result = "";
        if( value >= 2 && value <= 10 ) result += value;
        else if( value == 11 ) result += "J";
        else if( value == 12 ) result += "Q";
        else if( value == 13 ) result += "K";
        else if( value == 1 ) result += "A";
        else throw new RuntimeException("Invalid Card value: " + value);

        return result + suit;
    }
}
