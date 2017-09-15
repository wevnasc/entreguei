package br.com.wnascimento.entreguei.features.authentication;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface UserLocalRepository {

    Completable registerNew(User user);

    Maybe<User> getUserByEmailAndPassword(String email, String password);
}
