package is.model;

import lombok.Getter;

@Getter
public class Either<L, R> {

    private L left;
    private R right;

    public Either() {
    }

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    public Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    public Boolean isRight() {
        return this.left == null;
    }

    public Boolean isLeft() {
        return this.right == null;
    }

}
