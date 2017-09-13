package br.com.wnascimento.entreguei.features.authentication;

import io.reactivex.Completable;

public interface MotoboyRepository {

    Completable registerNew(Motoboy motoboy);

    Completable authenticate(Motoboy motoboy);

}
