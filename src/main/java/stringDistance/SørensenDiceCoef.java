package stringDistance;

import info.debatty.java.stringsimilarity.SorensenDice;

public class SørensenDiceCoef  implements StringDistance {
    public double distance(String a, String b) {
        SorensenDice sorensenDice= new SorensenDice();
        return sorensenDice.distance(a,b);
    }
}
