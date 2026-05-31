void main() {
    Scanner input = new Scanner(System.in);

    int opcion = 0;
    while (opcion != UI.SALIR) {
        UI.ImprimirMenu();
        
        opcion = input.nextInt();
    }
}
