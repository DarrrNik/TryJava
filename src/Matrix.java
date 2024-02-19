public class Matrix {
    Matrix(){
        row = 3; col = 3;
        det = new ComplexNum(); isCalculated = false;
        matrix = new ComplexNum[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                matrix[i][j] = new ComplexNum();
            }
        }
    }
    Matrix(int row_, int col_) throws Exception {
        if (row_ < 0 || col_ < 0)
            throw new Exception("Sizes of matrix should be positive");
        row = row_; col = col_;
        det = new ComplexNum(); isCalculated = false;
        matrix = new ComplexNum[row][col];
        for (int i = 0; i < row; ++i){
            for (int j = 0; j < col; ++j){
                matrix[i][j] = new ComplexNum();
            }
        }
    }
    Matrix(Matrix exmp){
        row = exmp.row; col = exmp.col;
        isCalculated = exmp.isCalculated;
        det = exmp.det; count = exmp.count;
        matrix = new ComplexNum[row][col];
        for (int i = 0; i < row; ++i){
            System.arraycopy(exmp.matrix[i], 0, matrix[i], 0, col);
        }
    }

    public static Matrix Sum(Matrix lhs, Matrix rhs) throws Exception{
        if (lhs.row != rhs.row || lhs.col != rhs.col)
            throw new Exception("Sizes are not equal.");
        Matrix res = new Matrix(lhs.row, lhs.col);
        for (int i = 0; i < lhs.row; ++i){
            for (int j = 0; j < lhs.col; ++j){
                res.matrix[i][j] = ComplexNum.Sum(lhs.matrix[i][j], rhs.matrix[i][j]);
            }
        }
        return res;
    }
    public static Matrix Sub(Matrix lhs, Matrix rhs) throws Exception{
        if (lhs.row != rhs.row || lhs.col != rhs.col)
            throw new Exception("Sizes are not equal.");
        return Matrix.Sum(lhs, Matrix.Multi(rhs, -1));
    }
    public static Matrix Multi(Matrix lhs, Matrix rhs) throws Exception{
        if (lhs.col != rhs.row)
            throw new Exception("Column number of left matrix is not equal to row number of right one.");
        Matrix res = new Matrix(lhs.row, rhs.col);
        for (int i = 0; i < lhs.row; ++i){
            for (int j = 0; j < rhs.col; ++j){
                ComplexNum num = new ComplexNum();
                for (int k = 0; k < lhs.col; ++k){
                    num = ComplexNum.Sum(num, ComplexNum.Multi(lhs.matrix[i][k], rhs.matrix[k][j]));
                }
                res.matrix[i][j] = num;
            }
        }
        return res;
    }

    public static Matrix Multi(Matrix lhs, double rhs) throws Exception {
        Matrix temp = new Matrix(lhs.row, lhs.col);
        for (int i = 0; i < lhs.row; ++i){
            for (int j = 0; j < lhs.col; ++j){
                temp.matrix[i][j] = ComplexNum.Multi(lhs.matrix[i][j], rhs);
            }
        }
        return temp;
    }
    public static Matrix Multi(Matrix lhs, ComplexNum rhs) throws Exception {
        Matrix temp = new Matrix(lhs.row, lhs.col);
        for (int i = 0; i < lhs.row; ++i){
            for (int j = 0; j < lhs.col; ++j){
                temp.matrix[i][j] = ComplexNum.Multi(lhs.matrix[i][j], rhs);
            }
        }
        return temp;
    }

    public Matrix Transp(){
        Matrix temp = new Matrix(this);
        for (int i = 0; i < row; ++i){
            for (int j = 0; j < col; ++j){
                temp.matrix[j][i] = matrix[i][j];
            }
        }
        return temp;
    }
    @Override
    public String toString(){
        String res = "";
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col - 1; ++j){
                res += matrix[i][j].toString() + " | ";
            }
            res += matrix[i][col-1].toString() + "\n";
        }
        return res;
    }
    public void setElem(ComplexNum val, int m, int n) throws Exception {
        if (m < 0 || n < 0 || m >= row || n >= col)
            throw new Exception("Incorrect indexes.");
        matrix[m][n] = val;
        isCalculated = false;
    }
    public ComplexNum getElem(int m, int n) throws Exception {
        if (m < 0 || n < 0 || m >= row || n >= col)
            throw new Exception("Incorrect indexes.");
        return matrix[m][n];
    }
    public ComplexNum GetDet() throws Exception {
        if (col != row)
            throw new Exception("The matrix is not square(row != col).");
        if (isCalculated)
            return det;
        boolean[] isVisited = new boolean[col];
        DHelper(0, new ComplexNum(1, 0), isVisited);
        isCalculated = true;
        return det;
    }
    public boolean equals(Matrix exmp){
        if (row != exmp.row || col != exmp.col)
            return false;
        for (int i = 0; i < row; ++i){
            for (int j = 0; j < col; ++j){
                if (!matrix[i][j].equals(exmp.matrix[i][j]))
                    return false;
            }
        }
        return true;
    }

    private ComplexNum[][] matrix;
    private final int row;
    private final int col;
    private ComplexNum det;
    private boolean isCalculated;

    private int count = 0;
    private void DHelper(int row_, ComplexNum prevMultiNum, boolean[] isVisited){
        for (int i = 0; i < col; ++i){
            if (isVisited[i] || matrix[row_][i].equals(new ComplexNum()))
                continue;
            if (row - row_ == 2){
                int m = 0, n = 0;
                for (; m < col; ++m){
                    if (!isVisited[m])
                        break;
                }
                for (; n < col; ++n){
                    if (m == n)
                        continue;
                    if (!isVisited[n])
                        break;
                }
                if (count % 2 == 0)
                    det = ComplexNum.Sum(det, ComplexNum.Multi(prevMultiNum,
                            ComplexNum.Sub(
                                    ComplexNum.Multi(matrix[m][row-2], matrix[n][row-1]),
                                    ComplexNum.Multi(matrix[m][row-1], matrix[n][row-2])
                            )));
                else
                    det = ComplexNum.Sub(det, ComplexNum.Multi(prevMultiNum,
                            ComplexNum.Sub(
                                    ComplexNum.Multi(matrix[m][row-2], matrix[n][row-1]),
                                    ComplexNum.Multi(matrix[m][row-1], matrix[n][row-2])
                            )));
                ++count;
                break;
            }
            isVisited[i] = true;
            DHelper(row_ + 1, ComplexNum.Multi(matrix[row_][i], prevMultiNum), isVisited);
            isVisited[i] = false;
        }
    }
}
