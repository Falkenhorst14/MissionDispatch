# 🚨 MissionDispatch – Android App zur Einsatzkraftverwaltung

MissionDispatch ist eine Android-App zur Verwaltung und Anzeige von Einsatzkräften und Einsatzabschnitten – optimiert für Tablets mit einem übersichtlichen Mehrspaltenlayout.

---

## 📱 Funktionen

1. Dokumentation der beteiligten Einsatzkräfte
2. Übersicht und Detail-Ansicht einer Einsatzkraft mit ihren Qualifikationen
3. Erstellen von Einsatzabschnitten
4. Zuordnen von Einsatzkräften zu Einsatzabschnitten mit Dokumentation ihrer individuellen Einsatzzeit

---

## 🧩 Architekturübersicht

### PersonalActivity
- Enthält TabLayout
- Steuert drei Fragmente: `PersonalFragment`, `AbschnitteFragment`, `PersonalDetailFragment`

### Fragmente
- `PersonalFragment`: Zeigt eine stark customisierte RecyclerView-Liste aller Einsatzkräfte mit der Möglichkeit, Einsatzkräfte in den Einsatz zu versetzen oder sie auszutragen
- `AbschnitteFragment`: Zeigt eine RecyclerView-Liste aller Abschnitte
- `PersonalDetailFragment`: Zeigt Detailinfos einer einzelnen Einsatzkraft mit Möglichkeit der Erfassung einer individuellen Einsatzzeit und der Möglichkeit der Zuordnung zu einem Abschnitt

### AbschnittDetailActivity
- Wird über das `AbschnitteFragment` geöffnet
- Zeigt alle zugeordneten Einsatzkräfte eines Abschnitts
- Ermöglicht das Löschen eines Abschnittes

### Datenmodell-Klassen
- `Einsatzkraft`: Repräsentiert eine Einsatzkraft mit allen zugehörigen Feldern (Name, Qualifikationen, Zeit, etc.), Gettern, Settern und Hilfsfunktionen
- `Abschnitt`: Repräsentiert einen Einsatzabschnitt mit ID, Namen, Gettern, Settern und Hilfsfunktionen

---

## 🗄️ Datenbankstruktur (SQLite)

### Tabelle: Einsatzkraefte
| Spalte                        | Typ      |
|------------------------------|----------|
| id                           | INTEGER (PK) |
| vorname, nachname, telefon   | TEXT     |
| geburtsdatum                 | TEXT     |
| imEinsatz                    | INTEGER  |
| Qualifikationen (diverse)    | INTEGER/TEXT |
| einsatzkraft_abschnitt       | INTEGER  |
| einsatzkraft_zeitstart/-ende| TEXT     |

### Tabelle: Abschnitte
| Spalte        | Typ      |
|---------------|----------|
| id_abschnitte | INTEGER (PK) |
| name_abschnitte | TEXT   |

Alle Zugriffe erfolgen über eine zentrale `DBHandler`-Klasse, die Methoden zum Erstellen, Einfügen, Abfragen und Aktualisieren der Daten enthält.

---

## 🔧 Technologien

- Java (Android SDK)
- SQLite (lokale Datenbank)
- RecyclerView + Adapter Pattern
- Fragments, ViewPager, TabLayout
- Material Components

---

## ▶️ Installation & Nutzung

1. Klone das Repository:
   ```bash
   git clone https://github.com/dein-benutzername/mission-dispatch.git
