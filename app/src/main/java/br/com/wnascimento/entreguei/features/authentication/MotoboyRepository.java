package br.com.wnascimento.entreguei.features.authentication;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface MotoboyRepository {

    Completable registerNew(Motoboy motoboy);

    Maybe<Motoboy> getMotoboyByEmailAndPassword(String email, String password);
}
