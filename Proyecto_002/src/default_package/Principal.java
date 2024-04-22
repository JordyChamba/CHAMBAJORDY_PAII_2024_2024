package default_package;

public class Principal {
	public static void main(String[] args) {
		Geometria obj = new Geometria(new Circulo());
		Geometria obj2 = new Geometria(new Cuadrado());
		Geometria obj3 = new Geometria(new Triangulo());
	}
}
