package shikaiia.extension.public

// List Comprehension
infix fun <T> Collection<T>.where(pre: (T) -> Boolean): Collection<T> {
    return this.filter { x -> pre(x) }
}

// Infinity List
