import mongoose, { Schema } from 'mongoose'

const eventsSchema = new Schema({
  title: {
    type: String
  },
  date: {
    type: String
  },
  description: {
    type: String
  },
  time: {
    type: String
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

eventsSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      title: this.title,
      date: this.date,
      description: this.description,
      time: this.time,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Events', eventsSchema)

export const schema = model.schema
export default model
