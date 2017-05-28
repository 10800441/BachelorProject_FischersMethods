public class Cost
{
    private double [][] costMatrix;
    public Cost(int rows,int cols) {
        this.costMatrix = new double[rows+1][cols+1];
    }
    public void assignCost(double value, int row, int col) {
        this.costMatrix[row][col]=value;
    }
    public double cost(int row, int col) {
        return this.costMatrix[row][col];
    }
}