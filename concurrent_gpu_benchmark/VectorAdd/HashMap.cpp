#include "HashMap.h"
#include <stdlib.h>

HashMap::HashMap(int dimension, int capacity)
{
	_dimension = dimension;
	_size = 0;
	_max = capacity;
	_table = (struct HashNode**)calloc(capacity, sizeof(struct HashNode));
	for (int i = 0; i < capacity; i++) _table[i] = nullptr;
}

HashMap::~HashMap() {
	_size = 0;
	_max = 0;
	free(_table);
	_table = 0;
}

unsigned int HashMap::index(int x, int y, int z, int w)
{
	unsigned long long hash = ((unsigned long long)(x & 0xFFFF) << 48) + ((unsigned long long)(y & 0xFFFF) << 32) + ((unsigned long long)(w & 0xFFFF) << 16) + (w & 0xFFFF);
	return hash % (unsigned long long)_max;
}

void HashMap::add(int x, int y, int z, int w, double value)
{
	unsigned int i = index(x, y, z, w);
	struct HashNode aux = { x, y, z, w, value, _table[i] };
	struct HashNode* node = (struct HashNode*)malloc(sizeof(struct HashNode));
	if (!node) return;
	*node = aux;
	_table[i] = node;
	_size++;
}

double HashMap::get(int x, int y, int z, int w)
{
	struct HashNode* node = _table[index(x, y, z, w)];
	while (node) {
		if (node->x == x && node->y == y && node->z == z && node->w == w) {
			return node->value;
		}
		node = node->next;
	}
	return 0.0;
}

int HashMap::size()
{
	return _size;
}