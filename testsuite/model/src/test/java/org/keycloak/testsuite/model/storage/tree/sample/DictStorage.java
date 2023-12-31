/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.testsuite.model.storage.tree.sample;

import org.keycloak.models.map.common.AbstractEntity;
import org.keycloak.models.map.common.DeepCloner;
import org.keycloak.models.map.storage.MapStorage;
import org.keycloak.models.map.storage.QueryParameters;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 *
 * @author hmlnarik
 */
public class DictStorage<V extends AbstractEntity, M> implements MapStorage<V, M> {

    private final DeepCloner cloner;

    private final List<V> store;

     public DictStorage(DeepCloner cloner, List<V> store) {
        this.cloner = cloner;
        this.store = store;
    }

    List<V> getStore() {
        return store;
    }

    @Override
    public V create(V value) {
        V res = cloner.from(value);
        store.add(res);
        return res;
    }

    @Override
    public V read(String key) {
        return store.stream()
          .filter(e -> Objects.equals(e.getId(), key))
          .findFirst()
          .orElse(null);
    }

    @Override
    public Stream<V> read(QueryParameters<M> queryParameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(QueryParameters<M> queryParameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long delete(QueryParameters<M> queryParameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
