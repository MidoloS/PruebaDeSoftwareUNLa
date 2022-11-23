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

public class TestIntegracionJuego {
	private Jugador jugador;
	private Juego juego;
	private Nivel nivel;
	private List<Nivel> niveles = new ArrayList<>();
	private Mapa mapa;
	private Dificultad dificultad;
	private Posicion posicion;
	private AvionSimple avion;
	private List<Pista> pistas = new ArrayList<Pista>();
	private PistaSimple pista;
	private Posicion posicionPista;
	private Nivel nivelFinal;
	private AvionSimple avion2;
	private Mapa mapa2;
	
	@Before
	public void setUp() throws Exception {
		jugador = new Jugador("Sebastian Midolo");
		dificultad = new Dificultad(1,0,1);
		mapa = new Mapa();
		mapa2 = new Mapa();
		nivel = new Nivel(mapa,dificultad);
		nivelFinal = new Nivel(mapa2, new Dificultad(1, 0, 1));
		niveles.add(nivel);
		niveles.add(nivelFinal);
		juego = new Juego(jugador, niveles);
		
		
		posicion = new Posicion(5,7);
		posicionPista = new Posicion(50,50);
		avion = new AvionSimple(posicionPista,posicionPista,mapa);
		avion2 = new AvionSimple(posicionPista,posicionPista,mapa);
		
		pista = new PistaSimple(posicionPista);
		pistas.add(pista);
		
		mapa.setPistas(pistas);
		mapa2.setPistas(pistas);
		nivel.colocarAvionEnAire(avion);
		nivelFinal.colocarAvionEnAire(avion2);
	}
	
	@Test
	public void testDebeAparecerUnAvion() {
		juego.colocarAvion();
		assertTrue("El juego debe tener al menos un avion", nivel.tieneAvionesVolando());
	}
	
	@Test
	public void testAvanzarDeNivelSiSeAterrizaronTodosLosAviones() {
		juego.vivir();
		assertTrue("El juego debera aparecer un avion de forma aleatoria y hacer las comprobaciones necesarias", juego.estaJugandose());
		juego.vivir();
		juego.vivir();
		juego.vivir();
		assertTrue(!juego.estaJugandose());
	}
}
