package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DirectionTSP extends JPanel {

    private static int width = 800;
    private static int heigth = 800;
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);

    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private List<Double> scores;
    public static List<City> cityList = new ArrayList<>();
    public static List<Integer> solutionList = new ArrayList<>();


    public DirectionTSP(List<Double> scores) {
        this.scores = scores;
    }

    public DirectionTSP(List<City> cityList, List<Integer> solutionList) {
        for (int i = 0; i < cityList.size(); i++) {
            Double x = cityList.get(i).getX();
            Double y = cityList.get(i).getY();
            x = x + padding + labelPadding;
            y = heigth - (y + padding + labelPadding);
            cityList.get(i).setX(x);
            cityList.get(i).setY(y);
        }


        this.cityList = cityList;
        this.solutionList = solutionList;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        //set color for points
        g2.setPaint(Color.green);
        g2.setStroke(new BasicStroke(3));
        int firstIndexSolution = 0;
        int secondIndexSolution = 0;
        for (int i = 0; i < cityList.size(); i++) {
            int cityIndex=solutionList.get(i)+1;
            if (i == cityList.size() - 1) {
                firstIndexSolution = solutionList.get(cityList.size() - 1);
                secondIndexSolution = solutionList.get(0);
            } else {
                firstIndexSolution = solutionList.get(i);
                secondIndexSolution = solutionList.get(i + 1);
            }
            Double x1 = cityList.get(firstIndexSolution).getX()+5;
            Double y1 = cityList.get(firstIndexSolution).getY()+5;
            Double x2 = cityList.get(secondIndexSolution).getX()+5;
            Double y2 = cityList.get(secondIndexSolution).getY()+5;
            g2.setPaint(Color.green);
            g2.drawLine(x1.intValue(), y1.intValue(), x2.intValue(), y2.intValue());


            Font f = new Font("", Font.PLAIN, 15);
             Color titleColor = new Color(16, 10, 10, 200);
            g2.setColor(titleColor);
            g2.setFont(f);

            g2.drawString("city"+String.valueOf(cityIndex), x1.intValue()-10, y1.intValue()-10);
        }


        g2.setPaint(Color.black);

        // set points to the graph
        for (int i = 0; i < cityList.size(); i++) {
            double x1 = cityList.get(i).getX();
            double y1 = cityList.get(i).getY();
            g2.fill(new Ellipse2D.Double(x1, y1, 11, 11));
        }

        Font f = new Font("", Font.BOLD, 17);
        g2.setColor(Color.black);
        g2.setFont(f);
        g2.drawString("Best Direction In The Travelling Salesman Person Problem", 180, padding / 2 + 5);

    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Double> getScores() {
        return scores;
    }

    public static void createAndShowGui() {

        List<Double> scores = new ArrayList<>();

        Random random = new Random();
        int maxDataPoints = 500;
        int maxScore = 500;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add((double) random.nextDouble() * maxScore);
//            scores.add((double) i);
        }

        DirectionTSP mainPanel = new DirectionTSP(scores);
        mainPanel.setPreferredSize(new Dimension(width, heigth));
        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<Integer> getSolutionList() {
        return solutionList;
    }

    public void setSolutionList(List<Integer> solutionList) {
        this.solutionList = solutionList;
    }

}
