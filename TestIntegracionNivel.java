package Midolo;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import pista.Pista;
import pista.PistaSimple;
import avion.AvionSimple;
import copControl.Dificultad;
import copControl.Juego;
import copControl.Jugador;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;

public class TestIntegracionNivel {
	private Jugador jugador;
	private Juego juego;
	private Nivel nivel;
	private List<Nivel> niveles = new ArrayList<>();
	private Mapa mapa;
	private Dificultad dificultad;
	private Posicion posicion;
	private AvionSimple avionChoque1;
	private AvionSimple avionChoque2;
	private AvionSimple avionAterrizaje3;
	private List<Pista> pistas = new ArrayList<Pista>();
	private PistaSimple pista;
	private Posicion posicion2;
	private Posicion posicionPista;
	
	@Before
	public void setUp() throws Exception {
		jugador = new Jugador("Sebastian Midolo");
		mapa = new Mapa(); 
		nivel = new Nivel(mapa,dificultad);
		niveles.add(nivel);
		juego = new Juego(jugador, niveles);
		dificultad = new Dificultad(5,0,1);
		
		posicion = new Posicion(5,7);
		posicion2 = new Posicion(8,6);
		posicionPista = new Posicion(50,50);
		
		avionChoque1 = new AvionSimple(posicion,posicion2,mapa);
		avionChoque2 = new AvionSimple(posicion,posicion2,mapa);
		avionAterrizaje3 = new AvionSimple(posicionPista,posicionPista,mapa);
		
		pista = new PistaSimple(posicionPista);
		pistas.add(pista);
		
		mapa.setPistas(pistas);
		nivel.colocarAvionEnAire(avionChoque1);
		nivel.colocarAvionEnAire(avionChoque2);
		nivel.colocarAvionEnAire(avionAterrizaje3);
	}
	
	@Test
	public void testHayAvionesVolando() {
		assertTrue("Hay aviones volando", nivel.tieneAvionesVolando());
	}
	
	@Test
	public void testHayPistasCompatibles() {
		assertTrue("Debe haber al menos una pista compatible", nivel.tienePistaAdecuada(avionAterrizaje3));
	}
	
	@Test
	public void testAterrizarAviones() {
		assertTrue("El avion debe desaparecer si esta en condiciones de aterrizaje", nivel.aterrizarAviones() == 1);
	}
	
	@Test
	public void testAvionesChocaron() {
		assertTrue("Los aviones deben chocar si estan en la misma posicion", nivel.huboChoque());
		assertFalse("El juego esta perdido si dos aviones colisionan", nivel.estaGanado());
	}
}
