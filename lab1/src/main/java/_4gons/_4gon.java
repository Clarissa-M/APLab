package _4gons;

import shapes.Vec2d;

class _4gon {
	Vec2d a, b, c, d;

	_4gon(Vec2d a, Vec2d b, Vec2d c, Vec2d d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	float perimeter() {
		return a.minus(b).l2norm() + b.minus(c).l2norm() +
			c.minus(d).l2norm() + d.minus(a).l2norm();
	}
}

interface ValidityChecker {
	boolean isValid();
}

class Parallelogram extends _4gon implements ValidityChecker {

	Parallelogram(Vec2d a, Vec2d b, Vec2d c, Vec2d d) {
		super(a, b, c, d);  // reuse the constructor function
	}

	public boolean isValid() {
		//TODO: Insert logic for checking if this is a valid parallelogram
		return true;
	}

	float area() {
		return Math.abs(a.getX()*b.getY() - a.getY()*b.getX());
	}
}

class Rectangle extends Parallelogram {
	float l_a, l_b;

	Rectangle(Vec2d a, Vec2d b, Vec2d c, Vec2d d) {
		super(a, b, c, d);  // reuse the constructor function
		l_a = a.minus(b).l2norm();
		l_b = b.minus(c).l2norm();
	}

	public boolean isValid() {
		if (!super.isValid()) return false;
		//TODO: Insert logic for checking if this is a valid rectangle
		return true;
	}
}

class Square extends Rectangle {

	Square(Vec2d a, Vec2d b, Vec2d c, Vec2d d) {
		super(a, b, c, d);  // reuse the constructor function
	}
}
