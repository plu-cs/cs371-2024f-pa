package cardtrick;

import java.util.Set;

public class CardTrick {

    /**
     * Given an array of 5 cards, return the String representations of all permutations
     * of the 5 cards that could be used to perform the trick.  For example, given the
     * input of cards "4D 5H 10C 10D QH", the method would return the set:
     * { "[5H, QH, 10D, 10C, 4D]", "[10D, 4D, QH, 10C, 5H]" }
     *
     * @param cards an array of 5 Card objects.
     * @return the set of String representations (using Arrays.toString) of each permutation
     * of the given five cards that could be used to perform the trick.
     */
    private static Set<String> doTrick(Card[] cards) {

        // TODO: Implement this method
        // You can make as many helper methods (private static) as you need.  In particular,
        // you'll most likely want a method that generates all permutations of 5 elements.
        // Use one of the textbook's algorithms for this.  It is not acceptable to use another
        // algorithm that you may find on the internet.  The Johnson-Trotter algorithm is a
        // good choice.  Have your permutation method return a list of all permutations as
        // a list of arrays of int.
        //
        // Once you have all permutations, then permute the cards and test each one against
        // the rules of the trick.  Return a Set containing the String representations of
        // each possible solution (there may be more than one).  You can get such a String using
        // the Arrays.toString method.

        return null;
    }


    /**
     * Given a String representation of a hand, returns a Set of possible
     * orderings of the Cards.
     *
     * DO NOT MODIFY THIS METHOD.  Do your work in the above method
     *
     * @param hand String representation of 5 cards, example: "4D 5H 10C 10D QH"
     * @return a Set of orderings as Strings that can be used to perform the trick.
     */
    public static Set<String> doTrick(String hand) {
        Card[] cards = new Card[5];
        String[] parts = hand.split("\\s+");
        if( parts.length != 5 ) throw new IllegalArgumentException("Invalid hand: " + hand);

        for( int i = 0; i < 5; i++ ) {
            cards[i] = new Card(parts[i]);
        }
        return doTrick(cards);
    }
}
