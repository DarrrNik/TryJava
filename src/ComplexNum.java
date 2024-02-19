public class ComplexNum {
    ComplexNum(){
        re = 0;
        im = 0;
    }
    ComplexNum(double re_, double im_){
        re = re_;
        im = im_;
    }
    public static ComplexNum Sum(ComplexNum lhs, ComplexNum rhs){
        return new ComplexNum(lhs.re + rhs.re, lhs.im + rhs.im);
    }
    public static ComplexNum Sub(ComplexNum lhs, ComplexNum rhs){
        return new ComplexNum(lhs.re - rhs.re, lhs.im - rhs.im);
    }
    public static ComplexNum Multi(ComplexNum lhs, ComplexNum rhs){
        return new ComplexNum(lhs.re * rhs.re - lhs.im * rhs.im,
                lhs.re * rhs.im + lhs.im * rhs.re);
    }
    public static ComplexNum Multi(ComplexNum lhs, double rhs){
        return new ComplexNum(lhs.re * rhs, lhs.im * rhs);
    }
    public static ComplexNum Cjg(ComplexNum num){
        return new ComplexNum(num.re, -num.im);
    }

    @Override
    public String toString(){
        return re + " + " + im + " * i";
    }
    public boolean equals(ComplexNum num){
        return num.re == re && num.im == im;
    }

    public double getRe() {
        return re;
    }
    public void setRe(int re_) {
        re = re_;
    }

    public double getIm() {
        return im;
    }
    public void setIm(int im_) {
        im = im_;
    }

    private double re;
    private double im;
}
