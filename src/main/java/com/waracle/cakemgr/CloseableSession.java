package com.waracle.cakemgr;

import org.hibernate.Session;

class CloseableSession implements AutoCloseable {

    private final Session session;

    public CloseableSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void close() {
        session.close();
    }
}
