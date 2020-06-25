package com.example.mynotes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private Notedao noteDao;
    private LiveData<List<Note>> allNotes;


    NoteRepository(Application application) {

        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.notedao();
        allNotes = noteDao.getAllNotes();

    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private Notedao notedao;

        InsertNoteAsyncTask(Notedao notedao) {
            this.notedao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notedao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private Notedao notedao;

        UpdateNoteAsyncTask(Notedao notedao) {
            this.notedao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notedao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private Notedao notedao;

        DeleteNoteAsyncTask(Notedao notedao) {
            this.notedao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notedao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private Notedao notedao;

        DeleteAllNotesAsyncTask(Notedao notedao) {
            this.notedao = notedao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            notedao.deleteAllNotes();
            return null;
        }
    }
}
