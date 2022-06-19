package it.unicam.cs.ids.Casotto.Classi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import it.unicam.cs.ids.Casotto.Repository.PrenotazioniRepository;
import it.unicam.cs.ids.Casotto.Repository.PrezzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.Casotto.Repository.OmbrelloneRepository;

/**
 * Classe che rappresenta un gestore di {@link Ombrellone}
 *
 */
@Service
@SuppressWarnings( {"UnusedReturnValue", "BooleanMethodIsAlwaysInverted"} )
public class GestoreOmbrelloni {

	@Autowired
    private OmbrelloneRepository ombrelloneRepository;


	public Ombrellone creazioneOmbrellone(int numero, String fila){
        this.checkIsNull(numero, fila);
        return new Ombrellone(numero, fila);
    }
	
	public boolean modificheOmbrelloni(Ombrellone ombrellone, boolean cancella) {
        if(cancella) {
            this.cancellaOmbrellone(ombrellone);
            return true;
        }

        this.ombrelloneRepository.save(ombrellone);
        return true;
    }
	
	 private void cancellaOmbrellone(Ombrellone ombrellone) {
	        this.checkIsNull(ombrellone);
	        if(!ombrelloneRepository.existsById(ombrellone.getId())) return;

	        ombrelloneRepository.deleteById(ombrellone.getId());
	    }

    public List<Ombrellone> getAll() {
        return StreamSupport.stream(ombrelloneRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

	private void checkIsNull(Object ... objects) {
        for(Object obj: objects){
            if(obj == null){
                throw new NullPointerException("I parametri passati sono nulli");
            }
        }
    }
}
