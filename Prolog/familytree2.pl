% Male and Female relationships
male(varad).
male(prashant).
male(shekhar).
female(sarika).
female(manjusha).
female(kavita).

% Parent relationships
parent(prashant, varad).
parent(sarika, varad).
parent(prashant, kavita).
parent(sarika, kavita).
parent(shekhar, manjusha).
parent(shekhar, prashant).

% Sibling relationships
sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.

% Grandparent relationships
grandparent(X, Y) :- parent(X, Z), parent(Z, Y).

% Aunt and Uncle relationships
aunt(X, Y) :- female(X), sibling(X, Z), parent(Z, Y).
uncle(X, Y) :- male(X), sibling(X, Z), parent(Z, Y).

% Cousin relationships
cousin(X, Y) :- parent(Z, X), sibling(Z, W), parent(W, Y).

% Additional rules for family relationships
mother_of(X, Y) :- female(X), parent(X, Y).
father_of(X, Y) :- male(X), parent(X, Y).
sister_of(X, Y) :- female(X), sibling(X, Y).
brother_of(X, Y) :- male(X), sibling(X, Y).
grandparent_of(X, Y) :- grandparent(X, Y).
aunt_of(X, Y) :- aunt(X, Y).
uncle_of(X, Y) :- uncle(X, Y).
cousin_of(X, Y) :- cousin(X, Y).

% Define mother and father predicates
mother(sarika, varad).
father(prashant, varad).
