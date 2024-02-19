public class TryJava {
    public static void main(String[] args) throws Exception {
        ComplexNum z1 = new ComplexNum(3, 2);
        ComplexNum z2 = new ComplexNum(3, 2);

//COMPLEX NUM OPERATIONS:
        //SUM
        System.out.println(ComplexNum.Sum(z1, z2).toString());
        //SUBTRACTION
        System.out.println(ComplexNum.Sub(z1, z2).toString());
        //MULTIPLE BY COMPLEX NUMBER
        System.out.println(ComplexNum.Multi(z1, z2).toString());
        //MULTIPLY BY REAL NUMBER
        System.out.println(ComplexNum.Multi(z1, 5).toString());
        //COMPLEX CONJUGATE
        System.out.println(ComplexNum.Cjg(z1).toString());
        //IS z1 EQUALS TO z1: z1.im == z2.im && z1.re == z2.re
        System.out.println(z1.equals(z2));

        Matrix mat1 = new Matrix(3, 3);
        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3; ++j){
                mat1.setElem(new ComplexNum(1, 0), i, j);
            }
        }
        Matrix mat2 = new Matrix(mat1);

//MATRIX OPERATIONS:
        //SUM
        System.out.println(Matrix.Sum(mat1, mat2).toString());
        //SUBTRACTION
        System.out.println(Matrix.Sub(mat1, mat2).toString());
        //MULTIPLY BY MATRIX
        System.out.println(Matrix.Multi(mat1, mat2).toString());
        //MULTIPLE BY COMPLEX NUMBER
        System.out.println(Matrix.Multi(mat1, new ComplexNum(5, 0)).toString());
        //IS mat1 EQUALS TO mat2: mat1[i][j] == mat2[i][j]
        System.out.println(mat1.equals(mat2));
        //mat2[1][2] = 2 + 0 * i
        mat2.setElem(new ComplexNum(2, 0), 1, 2);
        System.out.println(mat2.toString());
        //TRANSPOSED MATRIX
        System.out.println(mat2.Transp().toString());
        mat1.setElem(new ComplexNum(12, 0), 0, 0);
        mat1.setElem(new ComplexNum(12, 0), 1, 1);
        mat1.setElem(new ComplexNum(12, 0), 2, 2);
        mat1.setElem(new ComplexNum(-27, 0), 2,1);
        System.out.println(mat1.toString());
        //DETERMINANT OF MATRIX
        System.out.println(mat1.GetDet());
    }
}
