package com.meet.jetnote.model

import java.time.LocalDateTime
import java.util.UUID

data class Note(
    var id: UUID = UUID.randomUUID(),
    var title: String,
    var description: String,
    var entryDate: LocalDateTime = LocalDateTime.now()
)
