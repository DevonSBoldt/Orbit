/**
 * A simple m x n matrix class.
 *
 * TODO All of the methods currently just return default values. You need to make them match the
 * Javadoc comments.
 * 
 * @author Devon Boldt
 * @version Sept. 2017
 */

public class Matrix {

  private int m, n;
  private double[][] M;

  public Matrix(double[][] array) {
    M = array;

    // Rows
    m = array.length;

    // Columns
    n = array[0].length;
  }

  /**
   * @return # of columns
   */
  public int nCols() {

    return n;
  }

  /**
   * @return # of rows.
   */
  public int nRows() {

    return m;
  }

  /**
   * @param i
   * @param j
   * @return The entry at row i column j.
   */
  public double entry(int i, int j) {

    return M[i][j];
  }

  /**
   * Computes the dot product of this matrix with the parameter that. (Return value is this . that)
   * Recall that the dot product is the typical matrix multiplication.
   * 
   * @param that
   * @throws BadDimensionException
   * @return this x that
   */
  public Matrix dot(Matrix that) throws UndefinedMatrixOpException {

    return new Matrix(dotHelper(this.M, that.M));
  }

  private double[][] dotHelper(double[][] thisMatrix, double[][] thatMatrix) {
    assert thisMatrix[0].length == thatMatrix.length : "width of matrix one must be equal to height of matrix two";
    double[][] product = new double[thisMatrix.length][thatMatrix[0].length];

    // fills output matrix with 0's
    for (short rows = 0; rows < thisMatrix.length; rows++) {
      for (short columns = 0; columns < thatMatrix[0].length; columns++) {
        product[rows][columns] = 0;
      }
    }

    // takes the dot product of the rows and columns and adds them to output matrix
    for (short i = 0; i < thisMatrix.length; i++) {
      for (short j = 0; j < thatMatrix[0].length; j++) {
        for (short k = 0; k < thisMatrix[0].length; k++) {
          product[i][j] += thisMatrix[i][k] * thatMatrix[k][j];
        }
      }
    }
    return product;
  }

  /**
   * Add this matrix to that and returns the result. (Return value is this + that)
   * 
   * @param that the matrix to add this matrix to.
   * @throws BadDimensionException If the dimension of the two matrices are not identical.
   * @return The sum of the this and that.
   */
  public Matrix plus(Matrix that) throws UndefinedMatrixOpException {

    if (this.nRows() != that.nRows() || this.nCols() != that.nCols()) {
      throw new UndefinedMatrixOpException("The matricies aren't compatable", this, that);
    }

    double[][] plusMatrix = new double[this.nRows()][this.nCols()];
    for (int i = 0; i < this.nRows(); i++) {
      for (int j = 0; j < this.nCols(); j++) {
        plusMatrix[i][j] = this.entry(i, j) + that.entry(i, j);
      }
    }
    return new Matrix(plusMatrix);
  }

  /**
   * @param theta The rotation angle.
   * @return The homogeneous rotation matrix for a given value for theta.
   */
  public static Matrix rotationH2D(double theta) {
    double[][] R =
        {{Math.cos(theta), -Math.sin(theta), 0}, {Math.sin(theta), Math.cos(theta), 0}, {0, 0, 1}};
    return new Matrix(R);
  }

  /**
   * @param tx The amount to translate in the x direction.
   * @param ty The amount to translate in the y direction.
   * @return The matrix representing a translation of tx, ty.
   */
  public static Matrix translationH2D(double tx, double ty) {
    return new Matrix(new double[][] {{1, 0, tx}, {0, 1, ty}, {0, 0, 1}});
  }

  /**
   * @param x The x coordinate
   * @param y The y coordinate
   * @return The column matrix representing in homogeneous coordinates the point (x, y).
   */
  public static Matrix vectorH2D(double x, double y) {
    double[][] V = {{x}, {y}};
    return new Matrix(V);
  }

  /**
   * @param n The dimension of the matrix. Recall that the identity matrix has 1's for any entry
   *        that is in the same row index as its column index, 0's everywhere else.
   * @return the nxn identity matrix
   */
  public static Matrix identity(int n) {

    // Invalid parameters check
    if (n < 0) {
      return null;
    }

    double[][] identityMatrix = new double[n][n];

    for (int a = 0; a < n; a++) {
      for (int b = 0; b < n; b++) {

        if (a == b) {
          identityMatrix[a][b] = 1;
        } else {
          identityMatrix[a][b] = 0;
        }
      }
    }

    return new Matrix(identityMatrix);

  }

  /**
   * Computes the mxn identity matrix which has 1's for every entry at the same row and column index
   * and 0 for all other entries.
   * 
   * @param m
   * @param n
   * @return the mxn identity matrix.
   */
  public static Matrix identity(int m, int n) {

    double[][] identityCheckMatrix = null;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        // Checking if the indexes are the same, if so they a
        if (i == j) {
          identityCheckMatrix[m][n] = identityCheckMatrix[1][1];
        } else {
          // Otherwise, the index are 0
          identityCheckMatrix[m][n] = identityCheckMatrix[0][0];
        }

      }
    }
    return new Matrix(identityCheckMatrix);
  }

  /**
   * A little helpful toString() in case you want to print your matrix to System.out
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        sb.append(M[i][j]);
        sb.append('\t');
      }
      sb.append('\n');
    }
    return sb.toString();
  }
}
