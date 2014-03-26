import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        int[] data = In.readInts(args[0]);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(.008);

        int n = data[0];
        Point[] pointsNatural = new Point[n];
        for (int i = 0; i < n; i++) {
            pointsNatural[i] = new Point(data[2*i + 1], data[2*i + 2]);
            pointsNatural[i].draw();
        }
        StdDraw.setPenRadius(.004);
        Arrays.sort(pointsNatural);

        //printOut(pointsNatural, n, "   ");
        //StdOut.print("\n");

        Point[] pointsSlope = new Point[n - 1];

        for (int p = 0; p < n; p++) {
            Point origin = pointsNatural[p];
            for (int i = 0; i < p; i++)
                pointsSlope[i] = pointsNatural[i];
            for (int i = p + 1; i < n; i++)
                pointsSlope[i - 1] = pointsNatural[i];

            Arrays.sort(pointsSlope, origin.SLOPE_ORDER);

            //printOut(pointsSlope, n - 1, "   ");

            for (int i = 1; i < n - 1; i++) {
                int count = 1;
                while ((i < n - 1) && (origin.slopeTo(pointsSlope[i]) == origin.slopeTo(pointsSlope[i - 1]))) {
                    count++;
                    i++;
                }
                if (count >= 3) {
                    Point[] answer = new Point[count + 1];
                    for (int j = i - count, k = 0; j < i; j++, k++)
                        answer[k] = pointsSlope[j];
                    answer[count] = origin;
                    Arrays.sort(answer);
                    if (answer[0] == origin) {
                        printOut(answer, count + 1, " -> ");
                    }
                }

                //i++;
            }
        }

    }

    private static void printOut(Point[] answer, int count, String split) {
        String outString = "";
        outString += answer[0];
        for (int i = 1; i < count; i++)
            outString += split + answer[i];
        StdOut.println(outString);
        answer[0].drawTo(answer[count - 1]);
    } 
}